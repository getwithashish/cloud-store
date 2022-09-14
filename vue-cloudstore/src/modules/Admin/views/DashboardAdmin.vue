<template>
  <div class="tile is-ancestor">
    <AdminDashboardCard data="Ashish" />
    <AdminDashboardCard data="Sam" />
    <AdminDashboardCard data="T" />
  </div>

  <div class="tile is-ancestor">
    <AdminDashboardCard data="George" />
    <AdminDashboardCard data="" />
    <AdminDashboardCard data="" />
  </div>
</template>

<script>
import AdminDashboardCard from "@/modules/Admin/components/AdminDashboardCard.vue";

export default {
  components: {
    AdminDashboardCard,
  },
};
</script>
