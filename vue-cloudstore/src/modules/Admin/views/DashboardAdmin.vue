<template>
  <div class="tile is-ancestor">
    <AdminDashboardCard data="Stock" />
    <AdminDashboardCard data="Sales" />
    <AdminDashboardCard data="Trend" />
  </div>

  <div class="tile is-ancestor">
    <AdminDashboardCard data="Log" />
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
