using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using Photon.Pun;
using Photon.Realtime;

// 포톤 Pun 서비스 이벤트 감지 : 메소드명 맞춰서 오버라이드 MonoBehaviourPunCallbacks
public class LobbyManager : MonoBehaviourPunCallbacks
{
    private readonly string gameVersion = "1.0";

    public Text connectionInfoText;
    public Button joinButton;
    public Text userName;

    List<RoomInfo> myList = new List<RoomInfo>();
    int currentPage = 1, maxPage, multiple;

    public InputField RoomInput;
    public Text WelcomeText;
    public Text LobbyInfoText;
    public Button[] CellBtn;
    public Button PreviousBtn;
    public Button NextBtn;

    // Start is called before the first frame update
    void Start()
    {
        // 게임 버전이 맞아야 만날 수 있음
        PhotonNetwork.GameVersion = gameVersion;
        // 설정 정보를 가지고 마스터 서버에 접속 시도
        PhotonNetwork.ConnectUsingSettings();

        //접속 버튼 비활성화
        joinButton.interactable = false;
        connectionInfoText.text = "마스터 서버에 접속 중......";

    }
    // 마스터 서버에 접속 성공했을 때 
    public override void OnConnectedToMaster()
    {
        joinButton.interactable = true;
        connectionInfoText.text = "온라인 : 마스터 서버와 연결됨";
    }
    // 로비 연결
    public void JoinLobby()
    {
        PhotonNetwork.JoinLobby();
    }
    // 로비 연결 성공했을 때
    public override void OnJoinedLobby()
    {
        PhotonNetwork.LocalPlayer.NickName = userName.text;
        WelcomeText.text = PhotonNetwork.LocalPlayer.NickName + "님 환영합니다!";
        connectionInfoText.text = "온라인 : 로비와 연결됨";
        myList.Clear();
    }

    // 마스터 서버에 실패 했을때 다시 접속
    public override void OnDisconnected(DisconnectCause cause)
    {
        joinButton.interactable = false;
        connectionInfoText.text = $"오프라인 : 마스터 서버와 연결되지 않음\n {cause.ToString()}";

        PhotonNetwork.ConnectUsingSettings();
    }

    // 방 만들기
    public void CreateRoom()
    {
        PhotonNetwork.CreateRoom(RoomInput.text == "" ? "Room" + Random.Range(0, 100) : RoomInput.text, new RoomOptions { MaxPlayers = 4 });
    }

    public void JoinRandomRoom() => PhotonNetwork.JoinRandomRoom();

    public override void OnJoinedRoom()
    {
        PhotonNetwork.LoadLevel("Main");
    }
    public override void OnCreateRoomFailed(short returnCode, string message) { RoomInput.text = ""; CreateRoom(); }

    public override void OnJoinRandomFailed(short returnCode, string message) { RoomInput.text = ""; CreateRoom(); }

    // 수동으로 메인에 있으면 조인룸 및 예외처리
    public void Connect()
    {
        joinButton.interactable = false;
        Debug.Log("이건 언제 시작되는데");
        if (PhotonNetwork.IsConnected)
        {
            connectionInfoText.text = "방에 연결 중....";
            PhotonNetwork.JoinRandomRoom();
        }
        else
        {
            connectionInfoText.text = "오프라인 : 마스터 서버와 연결되지 않음 \n 접속 재시도 중.....";

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
            Debug.Log("1");
            PhotonNetwork.JoinRoom(myList[multiple + num].Name);
        }
        MyListRenewal();
    }
    void Go()
    {
        // 행동 
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
            CellBtn[i].transform.GetChild(0).GetComponent<Text>().text = (multiple + i < myList.Count) ? myList[multiple + i].Name : "";
            CellBtn[i].transform.GetChild(1).GetComponent<Text>().text = (multiple + i < myList.Count) ? myList[multiple + i].PlayerCount + "/" + myList[multiple + i].MaxPlayers : "";
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

    }
}
