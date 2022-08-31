import ViewAdminCustomers from "@/modules/Admin/views/AdminCustomers/ViewAdminCustomers.vue"
import AddAdminCustomers from "@/modules/Admin/views/AdminCustomers/AddAdminCustomers.vue"
import EditAdminCustomers from "@/modules/Admin/views/AdminCustomers/EditAdminCustomers.vue";
import DisableAdminCustomers from "@/modules/Admin/views/AdminCustomers/DisableAdminCustomers.vue";
import EnableAdminCustomers from "@/modules/Admin/views/AdminCustomers/EnableAdminCustomers.vue";

export const adminCustomerRoutes = [{
    path: "customer",
    // components: {
    //     adminPage: ViewAdminCustomers
    // },
    children: [
        {
            path: "view",
            components: {
                adminPage: ViewAdminCustomers
            }
        },
        {
            path: "add",
            components: {
                adminPage: AddAdminCustomers
            }
        },
        {
            path: "edit",
            components: {
                adminPage: EditAdminCustomers
            }
        },
        {
            path: "disable",
            components: {
                adminPage: DisableAdminCustomers
            }
        },
        {
            path: "enable",
            components: {
                adminPage: EnableAdminCustomers
            }
        }
    ]
}
]