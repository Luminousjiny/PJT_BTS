<template>
	<div v-if="streamManager">
		<ov-video :stream-manager="streamManager"/>
		<div class="active-user-name" v-if="streamManager.stream.videoActive"><p>{{ clientData }}</p></div>
		<div class="inactive-user">
			<div class="inactive-user-info" v-if="!streamManager.stream.videoActive">
				<img src="@/../public/Image/school_icon.png" class="inactive-user-img">
				<div class="inactive-user-name"><p>{{ clientData }}</p></div>
			</div>
			<div class="inactive-user-video" v-if="!streamManager.stream.videoActive">
				<v-icon class="inactive">fas fa-video-slash</v-icon>
			</div>
			<div class="inactive-user-audio" v-if="!streamManager.stream.audioActive">
				<v-icon class="inactive">fas fa-microphone-slash</v-icon>
			</div>
		</div>
	</div>
</template>

<script>
import OvVideo from './OvVideo';
export default {
	name: 'UserVideo',
	components: {
		OvVideo,
	},
	props: {
		streamManager: Object,
	},
	computed: {
		clientData () {
			const { userName } = this.getConnectionData();
			return userName;
		},
	},
	methods: {
		getConnectionData () {
			const { connection } = this.streamManager.stream;
			return JSON.parse(connection.data);
		},
	},
};
</script>
<style scoped src="../../css/Camera.css">
</style>