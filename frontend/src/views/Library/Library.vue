<template>
    <div>
        <Nav/>
        <router-view/>
    </div>
</template>

<script>
import Nav from "@/common/Nav/Nav"
export default {
    name : 'Library',
    components : {
      Nav,
    },
}
</script>

<style>

</style>