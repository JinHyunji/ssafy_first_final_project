<template>
    <div>
        <h2>알람 설정 하기</h2>
        <div>
            <div>
                <label for="">알람 제목</label>
                <input type="text">
            </div>
            <div>
                <label for="">시작 시간</label>
                <input type="time">
            </div>
            <div>
                <label for="">종료 시간</label>
                <input type="time">
            </div>
            <div>
                <label for="">간격</label>
                <input type="number">
            </div>
            <div>
                <label for="">반복</label>
                <div>
                    <label>월</label>
                    <input value="yes" name="mon" type="checkbox">
                    <label>화</label>
                    <input value="yes" name="tue" type="checkbox">
                    <label>수</label>
                    <input value="yes" name="wed" type="checkbox">
                    <label>목</label>
                    <input value="yes" name="thur" type="checkbox">
                    <label>금</label>
                    <input value="yes" name="fri" type="checkbox">
                    <label>토</label>
                    <input value="yes" name="sat" type="checkbox">
                    <label>일</label>
                    <input value="yes" name="sun" type="checkbox">
                </div>
            </div>
            <div>
                <button>다음</button>
            </div>
        </div>
    </div>
</template>

<script setup>

</script>

<style scoped></style>