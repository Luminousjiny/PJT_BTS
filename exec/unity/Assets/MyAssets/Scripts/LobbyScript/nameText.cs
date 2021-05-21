using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;


public class nameText : MonoBehaviour
{
    private Text name;
    // Start is called before the first frame update
    void Start()
    {
        name = GetComponent<Text>();
    }

    // Update is called once per frame
    void Update()
    {
        if (BtnType.user != null)
        {
            name.text = $"{ BtnType.user} 님 환영합니다";
        }
    }
}
