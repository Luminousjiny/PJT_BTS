using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class LFollowCamera : MonoBehaviour
{
    public Transform target;
    private bool check = false;
    private int dir = 0;
    // Start is called before the first frame update
    void Start()
    {
     
    }
    void FixedUpdate()
    {
        if (check)
        {

            Vector3 Postion = new Vector3(target.transform.position.x, target.transform.position.y + 4.0f, target.transform.position.z - 7.0f);
            Vector3 dir = target.transform.position - this.transform.position;
            transform.position = Vector3.Lerp(transform.position, Postion, 3f * Time.deltaTime);
            transform.rotation = Quaternion.Lerp(this.transform.rotation, Quaternion.LookRotation(dir), Time.deltaTime * 3f);
            
           
            
        }
    }
    public void LeftClick()
    {
        Debug.Log(dir);
        dir--;
        if(dir == 0)
        {
            dir = 3;
        }
    }
    public void RightClick()
    {
        Debug.Log(dir);
        dir++;
        if (dir == 4)
        {
            dir = 1;
        }
    }
    public void LookMain()
    {
        dir = 1;
        check = true;
        GameObject[] mainTxt = GameObject.FindGameObjectsWithTag("LobbyText");
        for (int i = 0; i < mainTxt.Length; i++)
        {
            mainTxt[i].GetComponent<MeshRenderer>().enabled = true;
        }
        //GameObject mainBtn = GameObject.FindGameObjectWithTag("MainBtn");
        //mainBtn.GetComponent<Canvas>().enabled = true;

    }

    public void LookSetting()
    {

    }
}

