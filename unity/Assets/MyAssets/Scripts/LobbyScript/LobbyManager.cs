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
  
    // Start is called before the first frame update
    void Start()
    {
        // 게임 버전이 맞아야 만날 수 있음
        PhotonNetwork.GameVersion = gameVersion;
        // 설정 정보를 가지고 마스터 서버에 접속 시도
        PhotonNetwork.ConnectUsingSettings();

        //접속 버튼 비활성화
        joinButton.interactable = false;
        connectionInfoText.text = "Connection To Master Server....";
    }
    // 마스터 서버에 접속 성공했을 때 
    public override void OnConnectedToMaster()
    {
        joinButton.interactable = true;
        connectionInfoText.text = "Online : Connected to Master Server";
    }
    // 마스터 서버에 실패 했을때 다시 접속
    public override void OnDisconnected(DisconnectCause cause)
    {
        joinButton.interactable = false;
        connectionInfoText.text = $"Offline : Connection Disabled {cause.ToString()}";

        PhotonNetwork.ConnectUsingSettings();
    }
    // 수동으로 메인에 있으면 조인룸 및 예외처리
    public void Connect()
    {
        joinButton.interactable = false;
        Debug.Log("이건 언제 시작되는데");
        if (PhotonNetwork.IsConnected)
        {
            connectionInfoText.text = "Connection to Room....";
            PhotonNetwork.JoinRandomRoom();
        }
        else
        {
            connectionInfoText.text = "Offline : Connection Disabled - Try reconnecting....";

            PhotonNetwork.ConnectUsingSettings();
        }
    }
    // 아마 이 실패는 빈방이 없을경우 즉 처음일 경우
    public override void OnJoinRandomFailed(short returnCode, string message)
    {
        connectionInfoText.text = "There is no empty room, Creating new Room";
        // parm :  방이름 , 룸옵션 
        PhotonNetwork.CreateRoom(null, new RoomOptions { MaxPlayers = 5 });
    }


    // 방에 접속 성공했을때

    public override void OnJoinedRoom()
    {
        connectionInfoText.text = "Connected with Room.";
        // 여기서 씬 매니저로 가면 안됌 ex) SceneManager.LoadScene() : 독자적으로 이동하게 됌
        PhotonNetwork.LoadLevel("Main");
    }
    // Update is called once per frame
    void Update()
    {
        
    }
}
