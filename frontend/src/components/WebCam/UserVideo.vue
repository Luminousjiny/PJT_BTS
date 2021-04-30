<template>
	<div v-if="streamManager">
		<ov-video :stream-manager="streamManager"/>
		<div class="video-user-name"><p>{{ clientData }}</p></div>
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
