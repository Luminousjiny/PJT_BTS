using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using System.Runtime.InteropServices;

public class OnTrigger : MonoBehaviour
{
    GameObject nearObject;
    bool keyInput;

    [DllImport("__Internal")]
    private static extern void Hello();

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        GetInput();
    }
    void GetInput()
    {
        if (Input.GetKeyDown(KeyCode.E)&&nearObject!=null)
        {
            Interaction();
        }
    }
    void Interaction()
    {
        if(nearObject.tag == "Item")
        {
            Hello();
            Debug.Log("책을 읽읍시다.");
        }
    }
    private void OnTriggerEnter(Collider other)
    {
        if (other.tag == "Item")
        {
            nearObject = other.gameObject;
        }
    }
    private void OnTriggerExit(Collider other)
    {
        if(other.tag == "Item")
        {
            nearObject = null;

        }
    }
}
