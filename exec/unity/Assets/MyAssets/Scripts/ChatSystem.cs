using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using TMPro;
public class ChatSystem : MonoBehaviour
{
    public string currentSentence;
    public TextMeshPro text;
    public GameObject quad;
    private Transform temp;
    public void Ondialogue(string sentences, Transform chatPoint)
    {
        transform.position = chatPoint.position;
        currentSentence = sentences;
        StartCoroutine(DialogFlow(chatPoint));
    }
    public void Update()
    {
        transform.position = new Vector3(temp.position.x, temp.position.y, temp.position.z);
    }
    IEnumerator DialogFlow(Transform chatPoint)
    {
        if (currentSentence != null)
        {
            text.text = currentSentence;

            float x = text.preferredWidth;
            x = (x > 3) ? 3 : x + 0.3f;
            quad.transform.localScale = new Vector2(x, text.preferredHeight + 0.3f);
            temp = chatPoint;
            
            yield return new WaitForSeconds(3f);
        }
        Destroy(gameObject);
    }
}
