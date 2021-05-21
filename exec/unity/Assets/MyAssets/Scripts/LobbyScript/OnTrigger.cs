using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System.Runtime.InteropServices;
using TMPro;
using System;
using Photon.Pun;
using cakeslice;

public class OnTrigger : MonoBehaviourPun
{
    private TextMeshProUGUI locationInfo;
    GameObject nearObject;
    bool keyInput;
    bool hasBall = false;
    bool pingPong = false;

    [DllImport("__Internal")]
    private static extern void UnityfuncHook(string str);
   
    // Start is called before the first frame update
    void Start()
    {
        GameObject temp = GameObject.FindGameObjectWithTag("LocationInfo");
        locationInfo = temp.GetComponent<TextMeshProUGUI>();
    }

    // Update is called once per frame
    void Update()
    {
        GetInput();
        Animation();
    }


    void GetInput()
    {
        if (Input.GetKeyDown(KeyCode.E) && nearObject != null)
        {
            Interaction();
        }
    }
    void Interaction()
    {
        if (photonView.IsMine)
        {
            if (nearObject.tag == "Item")
            {
                item item = nearObject.GetComponent<item>();
                // Debug.Log("UnityHook : " + Convert.ToString(item.type));
                UnityfuncHook(Convert.ToString(item.type));
                /*
                Debug.Log("AddNumbers");
                AddNumbers(2, 3);
                Debug.Log("StringReturnValueFunction : ");
                StringReturnValueFunction();
                Debug.Log("Hello: ");
                Hello();
                */
            }
        }
    }
    private void Animation()
    {
        if (!photonView.IsMine)
        {
            return;
        }
        if (nearObject!=null&&nearObject.tag == "Item")
        {
            GameObject mainCamera = GameObject.FindWithTag("MainCamera");
            Color c = mainCamera.GetComponent<OutlineEffect>().lineColor0;
            if (pingPong)
            {
                c.a += Time.deltaTime;

                if (c.a >= 1)
                    pingPong = false;
            }
            else
            {
                c.a -= Time.deltaTime;

                if (c.a <= 0)
                    pingPong = true;
            }


            c.a = Mathf.Clamp01(c.a);
            mainCamera.GetComponent<OutlineEffect>().lineColor0 = c;
            mainCamera.GetComponent<OutlineEffect>().UpdateMaterialsPublicProperties();
        }
    }

    private void OnTriggerEnter(Collider other)
    {
        if (other.tag == "ball")
        {
            nearObject = other.gameObject;
        }
        if (photonView.IsMine)
        {
            if (other.tag == "Room")
            {
                nearObject = other.gameObject;
                RoomLocationScript location = nearObject.GetComponent<RoomLocationScript>();
            }
            if (other.tag == "Item")
            {
                nearObject = other.gameObject;
                item item = nearObject.GetComponent<item>();
                cakeslice.Outline outline = nearObject.GetComponent<cakeslice.Outline>();
                outline.eraseRenderer = false;

            }
        }
    }
    private void OnTriggerExit(Collider other)
    {
        if (other.tag == "ball")
        {
            nearObject = null;
        }
        if (photonView.IsMine)
        {
            if (other.tag == "Room")
            {
                nearObject = null;
                RoomLocationScript location = nearObject.GetComponent<RoomLocationScript>();
                locationInfo.text = null;
            }
            else if (other.tag == "Item")
            {
                nearObject = other.gameObject;
                item item = nearObject.GetComponent<item>();
                cakeslice.Outline outline = nearObject.GetComponent<cakeslice.Outline>();
                outline.eraseRenderer = true;

            }
        }
    }
}
