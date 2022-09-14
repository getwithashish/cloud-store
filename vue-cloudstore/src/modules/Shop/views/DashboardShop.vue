<template>
  <div class="tile is-ancestor">
    <ShopDashboardCard data="Jerin" />
    <ShopDashboardCard data="John" />
    <ShopDashboardCard data="Kaavumkal" />
  </div>

  <div class="tile is-ancestor">
    <ShopDashboardCard data="Oolampara" />
    <ShopDashboardCard data="Village" />
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
