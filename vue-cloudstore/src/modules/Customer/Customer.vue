<template>
<div class="section-contents">
  
    <CustomerPanel />
    <section class="section-main-contents">
        <router-view name="customerPage" />
    </section>

</div>
</template>

<script>
import CustomerPanel from "@/modules/Customer/components/CustomerPanel.vue"

export default {
    components: {
        CustomerPanel
    }
}
</script>

