<template>
  <div ref="flowContainer" class="flowContainer" />
</template>

<script>
import LogicFlow from '@logicflow/core'
// import "@logicflow/core/lib/style/index.css"
import '@logicflow/core/dist/style/index.css' // 2.0版本前的引入方式

export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: 'IvrFlowDetail',

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
      ]
    }
    this.lf = new LogicFlow({
      container: this.$refs.flowContainer,
      stopScrollGraph: true,
      stopZoomGraph: true,
      grid: {
        type: 'mesh'
      }
    })
    this.lf.render(data)
  }
}
</script>

<style scoped>
.flowContainer {
  display: flex;
  width: 100vw;
  height: 100vh;
  overflow: hidden;
}
</style>
