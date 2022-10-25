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


<style>
.section-contents {
  /* display: inline-flex; */
  padding-block-start: inherit;
  width: 100%;
}
.section-main-contents {
  margin-left: 30px;
  width: 80%;
}
</style>
