<template>
<div class="section-contents">
  
    <AdminPanel />
    <section class="section-main-contents">
        <router-view name="adminPage" />
    </section>

</div>
</template>

<script>
import AdminPanel from "@/modules/Admin/components/AdminPanel"

export default {
    components: {
        AdminPanel
    }
}
</script>

