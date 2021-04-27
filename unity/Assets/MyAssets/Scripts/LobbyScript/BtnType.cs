using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.EventSystems;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class BtnType : MonoBehaviour, IPointerEnterHandler, IPointerExitHandler
{
    public BTNType currentType;
    public Transform buttonScale;
    Vector3 defaultScale;
    bool isSound;
    bool isConn;
    public CanvasGroup mainGroup;
    public CanvasGroup optionGroup;
    public CanvasGroup connGroup;
    public InputField inputField;
    public static string user;
    private void Start()
    {
        defaultScale = buttonScale.localScale;
    }

    public void OnBtnClick()
    {

        switch (currentType)
        {
            case BTNType.Connection:
                CanvasGroupOn(mainGroup);
                CanvasGroupOff(connGroup);
                break;
            case BTNType.Start:
                CanvasGroupOff(mainGroup);
                CanvasGroupOff(connGroup);
                CanvasGroupOff(optionGroup);
                break;

            case BTNType.Option:
                CanvasGroupOn(optionGroup);
                CanvasGroupOff(mainGroup);
                break;
            case BTNType.Sound:
                if (isSound)
                {
                    Debug.Log("사운드ON");
                }
                else
                {
                    Debug.Log("사운드OFF");
                }
                isSound = !isSound;
                break;
            case BTNType.Char:
                break;
            case BTNType.Back:
                CanvasGroupOn(mainGroup);
                CanvasGroupOff(optionGroup);
                break;
            case BTNType.Quit:
                CanvasGroupOff(optionGroup);
                CanvasGroupOff(mainGroup);
                CanvasGroupOn(connGroup);
                break;
        }
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
    public void OnPointerEnter(PointerEventData eventData)
    {
        buttonScale.localScale = defaultScale * 1.1f;
    }

    public void OnPointerExit(PointerEventData eventData)
    {
        buttonScale.localScale = defaultScale;
    }
}
