using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class BgmScript : MonoBehaviour
{
    public Slider backVolume;
    public AudioSource audio;

    // Start is called before the first frame update

    private float backVol = 1f;

    void Start()
    {
        backVol = PlayerPrefs.GetFloat("backVol", 1f);
        backVolume.value = backVol;
        audio.volume = backVolume.value;
    }

    // Update is called once per frame
    void Update()
    {
        SoundSlider();
    }
    public void SoundSlider()
    {
        audio.volume = backVolume.value;

        backVol = backVolume.value;
        PlayerPrefs.SetFloat("backVol", backVol);
    }
}
