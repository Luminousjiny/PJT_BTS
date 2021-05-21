using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using Cinemachine;
using Photon.Pun;
using Photon.Realtime;
using UnityEngine.UI;
public class LookCam : MonoBehaviourPun
{
    // Start is called before the first frame update
    void Start()
    {
        TextMesh textMesh = GetComponent<TextMesh>();
        if (photonView.IsMine)
        {
            textMesh.text = PhotonNetwork.NickName;
        }
        else
        {
            textMesh.text = photonView.Owner.NickName;
        }
    }
    // Update is called once per frame
    void Update()
    {
        CinemachineVirtualCamera Cam =
                FindObjectOfType<CinemachineVirtualCamera>();
        transform.rotation = Cam.transform.rotation;

    }

}
