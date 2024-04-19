<template>
    <v-card outlined>
        <v-card-title>
            CompleteReturn
        </v-card-title>

        <v-card-text>
            <Number label="DeliveryId" v-model="value.deliveryId" :editMode="editMode"/>
            <String label="Status" v-model="value.status" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="completeReturn"
            >
                CompleteReturn
            </v-btn>
            
            <v-btn
                    color="primary"
                    text
                    @click="close"
            >
                Close
            </v-btn>
        </v-card-actions>
    </v-card>

</template>

<script>
   
    export default {
        name: 'CompleteReturnCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.deliveryId = 0;
            this.value.status = '';
        },
        watch: {
        },
        methods: {
            completeReturn() {
                this.$emit('completeReturn', this.value);
            },
            close() {
                this.$emit('closeDialog');
            },
            change() {
                this.$emit('input', this.value);
            },
        }
    }
</script>

