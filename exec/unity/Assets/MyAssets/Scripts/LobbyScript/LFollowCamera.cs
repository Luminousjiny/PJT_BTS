using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class LFollowCamera : MonoBehaviour
{
    public Transform target;
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
    private GameObject mainPlayer;

    // Start is called before the first frame update
    void Start()
    {
        scene1 = Quaternion.Euler(0, 0, 0);
        scene2 = Quaternion.Euler(0, -90, 90);
        scene3 = Quaternion.Euler(0, 0, 180);
        scene4 = Quaternion.Euler(90, 0, 90);
        mainPlayer = GameObject.FindGameObjectWithTag("Player");
        mainPlayer.active = false;
    }
    void FixedUpdate()
    {
        if (check)
        {
            float angle = Quaternion.Angle(mapObject.transform.rotation, this.transform.rotation);
            Vector3 Postion = new Vector3(target.transform.position.x, target.transform.position.y + 4.0f, target.transform.position.z - 7.0f);
            Vector3 dir = target.transform.position - this.transform.position;

            if (createChrCheck)
            {
                if (!create_temp)
                {
                    target.position = new Vector3(0, 2.0f, 0);
                }
                Postion = new Vector3(target.transform.position.x - 0.5f, target.transform.position.y + 2f, target.transform.position.z - 2.0f);
                transform.position = Vector3.Lerp(transform.position, Postion, 3f * Time.deltaTime);
                transform.rotation = Quaternion.Lerp(this.transform.rotation, Quaternion.LookRotation(dir), Time.deltaTime * 3f);
                create_temp = true;
            }
            else
            {
                mainPlayer.active = false;
                target.position = new Vector3(0, 1.6f, -0.6f);
                Postion = new Vector3(target.transform.position.x, target.transform.position.y + 4.0f, target.transform.position.z - 7.0f);
                transform.position = Vector3.Lerp(transform.position, Postion, 3f * Time.deltaTime);
                transform.rotation = Quaternion.Lerp(this.transform.rotation, Quaternion.LookRotation(dir), Time.deltaTime * 3f);
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
                case 5:
                    mapObject.transform.rotation = Quaternion.Lerp(mapObject.transform.rotation, scene4, Time.deltaTime * 3f);
                    break;
            }
            //GameObject mainPlayer = GameObject.FindGameObjectWithTag("Player");
            //mainPlayer.GetComponent<MeshRenderer>().enabled = true;
            //mainPlayer.transform.position = new Vector3(0, 1.6f, -0.6f);
            if (angle >= 29.5 && angle <= 30.5 && !createRoomCheck)
            {
                LookMain();
            }
            else if (angle >= 179 && angle <= 180.5 && !createChrCheck)
            {
                LookOption();
            }
            else if (angle >= 138 && angle <= 140)
            {
                LookManual();
            }else if(angle>= 105 && angle <= 111.5)
            {
                 mainPlayer.active = true;
            }
            else
            {
                CanvasGroupOff(manualGroup);
                CanvasGroupOff(mainGroup);
                CanvasGroupOff(optionGroup);
            }
        }
    }
    public void LeftClick()
    {
        createRoomCheck = false;
        createChrCheck = false;
        create_temp = false;
        lookDir--;
        if (lookDir <= 0)
        {
            lookDir = 3;
        }
    }
    public void RightClick()
    {
        createRoomCheck = false;
        createChrCheck = false;
        create_temp = false;
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
        lookDir = 1;
        check = true;
        GameObject[] mainTxt = GameObject.FindGameObjectsWithTag("LobbyText");
        for (int i = 0; i < mainTxt.Length; i++)
        {
            mainTxt[i].GetComponent<MeshRenderer>().enabled = true;
        }
        GameObject mainBtn = GameObject.FindGameObjectWithTag("MainBtn");
        mainBtn.GetComponent<Canvas>().enabled = true;
        target.transform.position = new Vector3(0, 1.6f, -0.6f);
    }
    public void LookMain()
    {
        createRoomCheck = false;
        CanvasGroupOff(manualGroup);
        CanvasGroupOff(optionGroup);
        CanvasGroupOff(createGroup);
        CanvasGroupOn(mainGroup);
    }
    public void LookOption()
    {
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
        lookDir = 5;
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

