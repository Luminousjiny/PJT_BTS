using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using TMPro;
public class ChatSystem : MonoBehaviour
{
    public string currentSentence;
    public TextMeshPro text;
    public GameObject quad;
    public void Ondialogue(string sentences, Transform chatPoint)
    {
        transform.position = chatPoint.position;
        currentSentence = sentences;
        StartCoroutine(DialogFlow(chatPoint));
    }
    
    IEnumerator DialogFlow(Transform chatPoint)
    {
        if (currentSentence != null)
        {
            text.text = currentSentence;
            Debug.Log(text.text);

            float x = text.preferredWidth;
            x = (x > 3) ? 3 : x + 0.3f;
            quad.transform.localScale = new Vector2(x, text.preferredHeight + 0.3f);

            transform.position = new Vector3(chatPoint.position.x, chatPoint.position.y ,chatPoint.position.z);

            yield return new WaitForSeconds(3f);
        }
        Destroy(gameObject);
    }
}
