<template>
    <div class="page-success">
        <div class="columns is-multiline">
            <div class="column is-12">
                <h1 class="title">Thank you for shopping</h1>

                <p>Your order will be processed and sent shortly</p>
                <p>Order ID: {{ orderId }}</p>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'Success',
    data(){
        return {
            orderId: this.$route.query.orderId
        }
    },
    mounted() {
        document.title = 'Success | CloudStore'
    },
}
</script>