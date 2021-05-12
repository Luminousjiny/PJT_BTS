<template>
    <div>
        <unity
        src = "/unity/Build/webGL.json"
        width ="1024"
        height = "768"
        unityLoader = "/unity/Build/UnityLoader.js"
        ref = "hookInstance"
        >

        </unity>
    </div>
</template>
<script>
import Unity from 'vue-unity-webgl'

export default {
    components: { Unity }
}
</script>
