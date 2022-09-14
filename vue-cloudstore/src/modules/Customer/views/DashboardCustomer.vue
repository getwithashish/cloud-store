<template>
  <div class="tile is-ancestor">
    <CustomerDashboardCard data="Ashish" />
    <CustomerDashboardCard data="Sam" />
    <CustomerDashboardCard data="T" />
  </div>

  <div class="tile is-ancestor">
    <CustomerDashboardCard data="George" />
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
