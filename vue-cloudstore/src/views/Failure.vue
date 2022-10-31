<template>
    <div class="page-success">
        <div class="columns is-multiline">
            <div class="column is-12">
                <h1 class="title">Some kind of error has occured</h1>

                <p>{{ message }}</p>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'Failure',
    data(){
        return {
            message: this.$route.query.errorMessage
        }
    },
    mounted() {
        document.title = 'Failed Payment | CloudStore'
    },
}
</script>