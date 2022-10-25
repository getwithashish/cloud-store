<template>
  <div class="tile is-ancestor">
    <ShopDashboardCard data="Stock" />
    <ShopDashboardCard data="Sales" />
    <ShopDashboardCard data="Trend" />
  </div>

  <div class="tile is-ancestor">
    <ShopDashboardCard data="Log" />
    <ShopDashboardCard data="" />
    <ShopDashboardCard data="" />
  </div>
</template>

<script>
import ShopDashboardCard from "@/modules/Shop/components/ShopDashboardCard.vue";

export default {
  components: {
    ShopDashboardCard,
  },
};
</script>
