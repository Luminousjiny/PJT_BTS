using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class item : MonoBehaviour
{
    public enum Type { WebCam, Audio, Metting,Study}
    public Type type;
    public int value;

    private void Update()
    {
        transform.Rotate(Vector3.up * 20 * Time.deltaTime);
    }
}
