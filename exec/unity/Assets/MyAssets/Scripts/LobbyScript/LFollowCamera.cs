using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using TMPro;

public class LFollowCamera : MonoBehaviour
{
    public GameObject mapObject;

    public BTNType currentType;
    public GameObject LobbyObject;
    public CanvasGroup mainGroup;
    public CanvasGroup optionGroup;
    public CanvasGroup connGroup;
    public CanvasGroup manualGroup;
    public CanvasGroup createGroup;


    private bool check = false;
    private bool createRoomCheck = false;
    private bool createChrCheck = false;
    private bool create_temp = false;

    private int lookDir = 0;
    private Quaternion scene1;
    private Quaternion scene2;
    private Quaternion scene3;
    private Quaternion scene4;

    private GameObject ScrollBtn;
    private GameObject CharExitBtn;

    // Start is called before the first frame update
    void Start()
    {
        scene1 = Quaternion.Euler(0, 0, 0);
        scene2 = Quaternion.Euler(0, -90, 90);
        scene3 = Quaternion.Euler(0, 0, 180);
        scene4 = Quaternion.Euler(90, 0, 90);

        ScrollBtn = GameObject.FindGameObjectWithTag("MainBtn");
        CharExitBtn = GameObject.FindGameObjectWithTag("CharExitBtn");
    }


    void FixedUpdate()
    {
        if (check)
        {
            float angle = Quaternion.Angle(mapObject.transform.rotation, this.transform.rotation);
            Vector3 dir = new Vector3(30.0f, 0f, 0f);


            if (createChrCheck)
            {
                Vector3 Postion = new Vector3(0f, -17f, -7.5f);
                transform.position = Vector3.Lerp(transform.position, Postion, 3f * Time.deltaTime);
                transform.rotation = Quaternion.Lerp(this.transform.rotation, Quaternion.Euler(dir), Time.deltaTime * 3f);

                if (ScrollBtn.GetComponent<Canvas>().enabled == true)
                {
                    ScrollBtn.GetComponent<Canvas>().enabled = false;
                    CharExitBtn.GetComponent<Canvas>().enabled = true;
                }

            }
            else
            {
                Vector3 Postion = new Vector3(0f, 5.6f, -7.5f);
                transform.position = Vector3.Lerp(transform.position, Postion, 3f * Time.deltaTime);
                transform.rotation = Quaternion.Lerp(this.transform.rotation, Quaternion.Euler(dir), Time.deltaTime * 3f);
                if (CharExitBtn.GetComponent<Canvas>().enabled == true)
                {
                    CharExitBtn.GetComponent<Canvas>().enabled = false;
                    ScrollBtn.GetComponent<Canvas>().enabled = true;
                }
            }



            switch (lookDir)
            {
                case 1:
                    mapObject.transform.rotation = Quaternion.Lerp(mapObject.transform.rotation, scene1, Time.deltaTime * 3f);
                    break;
                case 2:
                    mapObject.transform.rotation = Quaternion.Lerp(mapObject.transform.rotation, scene2, Time.deltaTime * 3f);
                    break;
                case 3:
                    mapObject.transform.rotation = Quaternion.Lerp(mapObject.transform.rotation, scene3, Time.deltaTime * 3f);
                    break;
            }
            //GameObject mainPlayer = GameObject.FindGameObjectWithTag("Player");
            //mainPlayer.GetComponent<MeshRenderer>().enabled = true;
            //mainPlayer.transform.position = new Vector3(0, 1.6f, -0.6f);
            LazeAround(angle);
        }
    }
    void LazeAround(float angle)
    {
        if (lookDir==1&&angle >= 29.5 && angle <= 30.5 && !createRoomCheck)
        {
            LookMain();
        }
        else if (lookDir == 3&&angle >= 179 && angle <= 180.5 && !createChrCheck)
        {
            LookOption();
        }
        else if (lookDir == 2&&angle >= 138 && angle <= 140)
        {
            LookManual();
        }
        else
        {
            CanvasGroupOff(manualGroup);
            CanvasGroupOff(mainGroup);
            CanvasGroupOff(optionGroup);
        }
    }
    public void LeftClick()
    {
        createRoomCheck = false;
        lookDir--;
        if (lookDir <= 0)
        {
            lookDir = 3;
        }
    }
    public void RightClick()
    {
        createRoomCheck = false;
        lookDir++;
        if (lookDir >= 4)
        {
            lookDir = 1;
        }
    }
    public void InitMain()
    {
        LobbyObject.GetComponent<Animator>().enabled = false;
        CanvasGroupOff(connGroup);
        createChrCheck = true;
        check = true;

        GameObject[] mainTxt = GameObject.FindGameObjectsWithTag("LobbyText");
        for (int i = 0; i < mainTxt.Length; i++)
        {
            mainTxt[i].GetComponent<MeshRenderer>().enabled = true;
        }

        GameObject mainBtn = GameObject.FindGameObjectWithTag("MainBtn");
        mainBtn.GetComponent<Canvas>().enabled = true;

    }

    public void LookMain()
    {
        lookDir = 1;
        createRoomCheck = false;
        createChrCheck = false;
        CanvasGroupOff(manualGroup);
        CanvasGroupOff(optionGroup);
        CanvasGroupOff(createGroup);
        CanvasGroupOn(mainGroup);
    }
    public void LookOption()
    {
        createChrCheck = false;
        CanvasGroupOff(manualGroup);
        CanvasGroupOff(mainGroup);
        CanvasGroupOff(createGroup);
        CanvasGroupOn(optionGroup);
    }
    public void LookManual()
    {
        CanvasGroupOff(optionGroup);
        CanvasGroupOff(mainGroup);
        CanvasGroupOff(createGroup);
        CanvasGroupOn(manualGroup);
    }
    public void LookCreat()
    {
        createRoomCheck = true;
        CanvasGroupOff(optionGroup);
        CanvasGroupOff(mainGroup);
        CanvasGroupOff(manualGroup);
        CanvasGroupOn(createGroup);
    }
    public void LookCharacter()
    {
        createChrCheck = true;
        CanvasGroupOff(optionGroup);

    }
    public void CanvasGroupOn(CanvasGroup cg)
    {
        cg.alpha = 1;
        cg.interactable = true;
        cg.blocksRaycasts = true;
    }
    public void CanvasGroupOff(CanvasGroup cg)
    {
        cg.alpha = 0;
        cg.interactable = false;
        cg.blocksRaycasts = false;
    }
}

