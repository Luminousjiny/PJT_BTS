using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System.Runtime.InteropServices;
using UnityEngine.UI;
using Photon.Pun;
using Photon.Realtime;
using TMPro;
using System;

// 포톤 Pun 서비스 이벤트 감지 : 메소드명 맞춰서 오버라이드 MonoBehaviourPunCallbacks
public class LobbyManager : MonoBehaviourPunCallbacks
{
    private readonly string gameVersion = "1.0";

    [DllImport("__Internal")]
    private static extern void UnityroomHook(string str);
    [DllImport("__Internal")]
    private static extern void UnityuserHook(string str);

    public TextMeshProUGUI connectionInfoText;
    public Button connectionBtn;

    List<RoomInfo> myList = new List<RoomInfo>();
    int currentPage = 1, maxPage, multiple;

    public TMP_InputField RoomNameInput;
    public TMP_InputField RoomPullInput;
    public TMP_InputField playerName;

    public Toggle RoomprivateToggle;

    public TextMeshProUGUI WelcomeText;
    public Button[] CellBtn;
    public Button PreviousBtn;
    public Button NextBtn;
    public Button changeUsernameBtn;
    public TextMeshProUGUI linkdText;

    private int confirm=0;
    // Start is called before the first frame update
    void Start()
    {
        // 게임 버전이 맞아야 만날 수 있음
        PhotonNetwork.GameVersion = gameVersion;
        // 설정 정보를 가지고 마스터 서버에 접속 시도
        PhotonNetwork.ConnectUsingSettings();

        //접속 버튼 비활성화
        connectionBtn.interactable = false;
        connectionInfoText.text = "Connecting to the Master Server......";

        InvokeRepeating("confirmLink", 0.5f,0.5f);

    }
    private void confirmLink()
    {
        if (confirm == 0)
        {
            linkdText.text = "계정 연동을 위해 BTS 웹 페이지의 \n\n 연동 버튼을 클릭해주세요.....";
            confirm++;
        }else if (confirm == 1)
        {
            linkdText.text = "계정 연동을 위해 BTS 웹 페이지의 \n\n 연동 버튼을 클릭해주세요.";
            confirm++;
        }
        else if (confirm == 2)
        {
            linkdText.text = "계정 연동을 위해 BTS 웹 페이지의 \n\n 연동 버튼을 클릭해주세요..";
            confirm++;
        }
        else if (confirm == 3)
        {
            linkdText.text = "계정 연동을 위해 BTS 웹 페이지의 \n\n 연동 버튼을 클릭해주세요...";
            confirm++;
        }
        else if (confirm == 4)
        {
            linkdText.text = "계정 연동을 위해 BTS 웹 페이지의 \n\n 연동 버튼을 클릭해주세요....";
            confirm = 0;
        }
    }
    // 마스터 서버에 접속 성공했을 때 
    public override void OnConnectedToMaster()
    {
        //connectionBtn.interactable = true;
        connectionInfoText.text = "Online: Connected to the Master Server";
    }
    // 로비 연결
    public void JoinLobby()
    {
        CancelInvoke("confirmLink");
        PhotonNetwork.JoinLobby();
    }
    // 로비 연결 성공했을 때
    public override void OnJoinedLobby()
    {
        if(PhotonNetwork.LocalPlayer.NickName == ""|| PhotonNetwork.LocalPlayer.NickName == null)
        {
            PhotonNetwork.LocalPlayer.NickName = "대전 B107";
        }
        connectionInfoText.text = "Online: Connected to the Lobby";
        myList.Clear();
    }

    // 마스터 서버에 실패 했을때 다시 접속
    public override void OnDisconnected(DisconnectCause cause)
    {
        connectionBtn.interactable = false;
        connectionInfoText.text = $"Offline: No connection with the master server\n {cause.ToString()}";

        PhotonNetwork.ConnectUsingSettings();
    }
    public void changePlayerName()
    {
        if (playerName.text == "" || playerName.text == null)
        {
            playerName.text = "대전 B107";
        }else
        {
            PhotonNetwork.LocalPlayer.NickName = playerName.text;
        }
    }
    public void initPlayerName(string initName)
    {
        if (initName!=null)
        {
            PhotonNetwork.LocalPlayer.NickName = initName;
            connectionBtn.interactable = true;
        }
    }
    // 방 만들기
    public void CreateRoom()
    {
        string maxPlayer = RoomPullInput.text;
        if (maxPlayer == "")
        {
            maxPlayer = "4";
        }
        if(RoomNameInput.text == "")
        {
            string RoomName = "School" + UnityEngine.Random.Range(0, 100);
            UnityroomHook(RoomName);
            PhotonNetwork.CreateRoom(RoomName, new RoomOptions { MaxPlayers = Convert.ToByte(maxPlayer), IsVisible = !RoomprivateToggle.isOn, CleanupCacheOnLeave = true });
        }
        else
        {
            UnityroomHook(RoomNameInput.text);
            PhotonNetwork.CreateRoom(RoomNameInput.text, new RoomOptions { MaxPlayers = Convert.ToByte(maxPlayer), IsVisible = !RoomprivateToggle.isOn, CleanupCacheOnLeave = true });
        }


    }

    public void JoinRandomRoom() => PhotonNetwork.JoinRandomRoom();

    public override void OnJoinedRoom()
    {
        PhotonNetwork.LoadLevel("Main");
    }
    public override void OnCreateRoomFailed(short returnCode, string message) { RoomNameInput.text = ""; CreateRoom(); }

    public override void OnJoinRandomFailed(short returnCode, string message) { RoomNameInput.text = ""; CreateRoom(); }

    // 수동으로 메인에 있으면 조인룸 및 예외처리
    public void Connect()
    {
        connectionBtn.interactable = false;
        if (PhotonNetwork.IsConnected)
        {
            connectionInfoText.text = "Connecting to the School....";
            PhotonNetwork.JoinRandomRoom();
        }
        else
        {
            connectionInfoText.text = "Offline: No connection with the master server \n Retrying connection.....";
            PhotonNetwork.ConnectUsingSettings();
        }
    }

    // ◀버튼 -2 , ▶버튼 -1 , 셀 숫자
    public void MyListClick(int num)
    {
        if (num == -2) --currentPage;
        else if (num == -1) ++currentPage;
        else
        {
            UnityroomHook(myList[multiple + num].Name);
            PhotonNetwork.JoinRoom(myList[multiple + num].Name);
        }
        MyListRenewal();
    }
    void MyListRenewal()
    {
        // 최대페이지
        maxPage = (myList.Count % CellBtn.Length == 0) ? myList.Count / CellBtn.Length : myList.Count / CellBtn.Length + 1;
        // 이전, 다음버튼
        PreviousBtn.interactable = (currentPage <= 1) ? false : true;
        NextBtn.interactable = (currentPage >= maxPage) ? false : true;

        // 페이지에 맞는 리스트 대입
        multiple = (currentPage - 1) * CellBtn.Length;
        for (int i = 0; i < CellBtn.Length; i++)
        {
            CellBtn[i].interactable = (multiple + i < myList.Count) ? true : false;
            CellBtn[i].transform.GetChild(0).GetComponent<TextMeshProUGUI>().text = (multiple + i < myList.Count) ? myList[multiple + i].Name : "";
            CellBtn[i].transform.GetChild(1).GetComponent<TextMeshProUGUI>().text = (multiple + i < myList.Count) ? myList[multiple + i].PlayerCount + "/" + myList[multiple + i].MaxPlayers : "";
        }
    }
    public override void OnRoomListUpdate(List<RoomInfo> roomList)
    {
        int roomCount = roomList.Count;
        for (int i = 0; i < roomCount; i++)
        {
            if (!roomList[i].RemovedFromList)
            {
                if (!myList.Contains(roomList[i])) myList.Add(roomList[i]);
                else myList[myList.IndexOf(roomList[i])] = roomList[i];
            }
            else if (myList.IndexOf(roomList[i]) != -1) myList.RemoveAt(myList.IndexOf(roomList[i]));
        }
        MyListRenewal();
    }

    // Update is called once per frame
    void Update()
    {
        if (PhotonNetwork.LocalPlayer.NickName == playerName.text)
        {
            changeUsernameBtn.interactable = false;
        }else
        {
            changeUsernameBtn.interactable = true;
        }

    }
}
