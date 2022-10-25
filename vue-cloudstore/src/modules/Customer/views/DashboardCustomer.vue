<template>
  <div class="tile is-ancestor">
    <CustomerDashboardCard data="Stock" />
    <CustomerDashboardCard data="Sales" />
    <CustomerDashboardCard data="Trend" />
  </div>

  <div class="tile is-ancestor">
    <CustomerDashboardCard data="Log" />
    <CustomerDashboardCard data="" />
    <CustomerDashboardCard data="" />
  </div>
</template>

<script>
import CustomerDashboardCard from "@/modules/Customer/components/CustomerDashboardCard.vue";

export default {
  components: {
    CustomerDashboardCard,
  },
};
</script>
