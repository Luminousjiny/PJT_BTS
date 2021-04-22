using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using Cinemachine;
public class LookCam : MonoBehaviour
{
    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        CinemachineVirtualCamera Cam =
                FindObjectOfType<CinemachineVirtualCamera>();
        transform.rotation = Cam.transform.rotation;
            
    }
}
