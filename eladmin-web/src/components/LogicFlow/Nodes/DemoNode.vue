<template>
  <div class="demo-wrp">
    <div class="icon-div">
      <svg-icon icon-class="Steve-Jobs" />
    </div>
    <div class="desc-div">
      <p class="title">DEMO_NODE</p>
      <p class="title-1">数量：<span>1000</span></p>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {

    }
  }
}
</script>
<style scoped>
.demo-wrp{
  width: 240px;
  height: 80px;
  border: 1px solid rgba(255, 180, 30, 1);
  background: rgba(255, 180, 30, 0.15);
  padding: 12px 24px;
  border-radius: 8px;
  display: flex;
  gap: 8px;

  .icon-div{
    width: 40px;
    height: 40px;
    border-radius: 20px;
    display: flex;
    align-items: center;
    justify-content: center;
    margin-top: 5px;
    background: linear-gradient(180deg, #fedc47 0%, rgba(230, 180, 14, 0.8) 100%);
    .demo-icon{
      width: 16px;
      height: 20px;
    }
  }

  .desc-div{
    height: 52px;
    .title{
      font-size: 16px;
      font-weight: 600;
      line-height: 24px;
      text-align: left;
      color: rgb(255, 180, 30);
      white-space: nowrap;
      width: 130px;
      overflow: hidden;
      text-overflow: ellipsis;
      margin-bottom: 4px;
    }
    .title-1{
      font-size: 14px;
      font-weight: 400;
      line-height: 24px;
      text-align: left;
      color: rgb(255, 180, 30);
      white-space: nowrap;
      width: 130px;
      text-overflow: ellipsis;
    }
  }

}
</style>
