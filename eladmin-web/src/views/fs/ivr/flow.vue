<template>
  <div ref="flowContainer" class="flowContainer">
    <div class="wrp">
      <div class="wrp-menu">
        <LeftMenu :task-detail="taskDetail" />
      </div>
      <div class="wrp-container">
        <div class="wrp-control" />
        <div ref="diagram" class="wrp-canvas" />
      </div>
    </div>
  </div>
</template>

<script>
import { LogicFlow } from '@logicflow/core'
// import "@logicflow/core/lib/style/index.css"
import '@logicflow/core/dist/style/index.css' // 2.0版本前的引入方式
import LeftMenu from '@/components/LogicFlow/LeftMenu.vue'

export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: 'IvrFlowDetail',

  components: {
    LeftMenu
  },

  data() {
    return {
      taskDetail: {}
    }
  },

  mounted() {
    const data = {
      // 节点
      nodes: [
        {
          id: 50,
          type: 'rect',
          x: 100,
          y: 150,
          text: '你好'
        },
        {
          id: 21,
          type: 'circle',
          x: 300,
          y: 150
        }
      ],
      // 边
      edges: [
        {
          type: 'polyline',
          sourceNodeId: 50,
          targetNodeId: 21
        }
      ],
      taskDetail: {

      }
    }
    this.lf = new LogicFlow({
      container: this.$refs.diagram,
      stopScrollGraph: true,
      stopZoomGraph: true,
      grid: {
        type: 'mesh'
      }
    })
    this.lf.render(data)
    this.lf.translateCenter()
  }
}
</script>

<style scoped>
.flowContainer {
  width: 100vw;
  height: 90vh;
  background-color: #eaeaea;
}

.wrp {
  width: 100%;
  height: 100%;
  overflow: hidden;
  display: flex;
  align-items: flex-start;
  justify-content: flex-start;

  .wrp-menu {
    width: 250px;
    height: 100%;
    overflow-y: auto;
    background-color: #f3f3f3;
    border-right: 1px solid #ddd;

    &::-webkit-scrollbar {
      width: 6px;
      background-color: #eeeeee;
    }

    &::-webkit-scrollbar-thumb {
      border-radius: 10px;
      background: #ccc;
    }
  }

  .wrp-container {
    flex: 1;
    height: 100%;
    background-color: #ffffff;

    .wrp-control {
      width: 100%;
      height: 50px;
      display: flex;
      align-items: center;
      justify-content: flex-end;
      border-bottom: 1px solid #ddd;
      padding: 0 20px;
      background-color: #42b983;
      color: white;
    }

    .wrp-canvas {
      width: 100%;
      height: calc(100% - 50px);
      background-color: #ffffff;
    }
  }
}
</style>
