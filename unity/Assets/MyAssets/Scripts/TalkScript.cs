using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TalkScript : MonoBehaviour
{
    public string sentences;
    public Transform charTr;
    public GameObject chatBoxPrefab;
    // Start is called before the first frame update
    public void Start()
    {

    }
    public void Update()
    {
        if (Input.GetKeyDown(KeyCode.Z))
        {
            Talk();
        }
    }
    // Update is called once per frame
    public void Talk()
    {
        GameObject go = Instantiate(chatBoxPrefab);
        go.GetComponent<ChatSystem>().Ondialogue(sentences, charTr);
    }
}
