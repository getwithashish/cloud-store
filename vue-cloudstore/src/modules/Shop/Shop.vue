<template>
<div class="section-contents">
  
    <ShopPanel />
    <section class="section-main-contents">
        <router-view name="shopPage" />
    </section>

</div>
</template>

<script>
import ShopPanel from "@/modules/Shop/components/ShopPanel.vue"

export default {
    components: {
        ShopPanel
    }
}
</script>

