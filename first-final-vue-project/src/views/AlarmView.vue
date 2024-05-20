<template>
    <TheHeaderNav/>
    <div>
        <RouterView />
    </div>
    
</template>

<script setup>
import TheHeaderNav from '@/components/common/TheHeaderNav.vue';
import AlarmList from '@/components/alarm/AlarmList.vue';

import {useAlarmStore} from '@/stores/alarm'

const store = useAlarmStore();

</script>

<style scoped>

</style>