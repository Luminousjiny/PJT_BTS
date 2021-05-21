using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using Cinemachine;
using Photon.Pun;
using TMPro;

public class TalkScript : MonoBehaviourPun
{
    public TextMeshPro chat;
    public static string s_chat = null;
    public static string p_id;
    // Start is called before the first frame update
    public void Start()
    {
        p_id = PhotonNetwork.LocalPlayer.UserId;
    }
    public void setId(string id)
    {
        p_id = id;
    }
    public  string getId()
    {
        return p_id;
    }
    public void Update()
    {
        CinemachineVirtualCamera Cam =
                       FindObjectOfType<CinemachineVirtualCamera>();
        chat.transform.rotation = Cam.transform.rotation;
        if (s_chat != null)
        {
            chat.text = s_chat;
            Invoke("initChat", 3.0f);
            s_chat = null;
        }
    }public void initChat()
    {
        chat.text = null;
    }
    // Update is called once per frame
    public void Talk(string text)
    {
        s_chat = text;
    }
}
