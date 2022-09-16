<template>
  <div class="tile is-ancestor">
    <div class="tile is-vertical is-9">
      <div class="tile is-parent">
        <article class="tile is-child">
          <div class="content">
            <div class="form-button">
              <button class="button is-primary" @click="this.formModeToggle">
                {{ formModeButton }}
              </button>
            </div>
            <fieldset
              v-bind="{
                disabled: this.formMode == 'disabled',
              }"
            >
              <div class="field is-horizontal">
                <div class="field-label is-normal">
                  <label class="label">Email</label>
                </div>
                <div class="field-body">
                  <div class="field">
                    <div class="control">
                      <input
                        class="input"
                        type="email"
                        v-bind:value="email"
                        placeholder="Email"
                      />
                    </div>
                  </div>
                </div>
              </div>

              <div class="field is-horizontal">
                <div class="field-label is-normal">
                  <label class="label">Mobile Number</label>
                </div>
                <div class="field-body">
                  <div class="field">
                    <div class="control">
                      <input
                        class="input"
                        type="number"
                        v-bind:value="mobile"
                        placeholder="Mobile Number"
                      />
                    </div>
                  </div>
                </div>
              </div>

              <div class="field is-horizontal">
                <div class="field-label is-normal">
                  <label class="label">Role</label>
                </div>
                <div class="field-body">
                  <div class="field">
                    <div class="control">
                      <input
                        class="input"
                        type="text"
                        v-bind:value="role"
                        placeholder="Role"
                        disabled
                      />
                    </div>
                  </div>
                </div>
              </div>

              <div class="field is-horizontal">
                <div class="field-label is-normal">
                  <label class="label">House Name</label>
                </div>
                <div class="field-body">
                  <div class="field">
                    <div class="control">
                      <input
                        class="input"
                        type="text"
                        v-bind:value="house"
                        placeholder="House Name"
                      />
                    </div>
                  </div>
                </div>
              </div>

              <div class="field is-horizontal">
                <div class="field-label is-normal">
                  <label class="label">Street Name</label>
                </div>
                <div class="field-body">
                  <div class="field">
                    <div class="control">
                      <input
                        class="input"
                        type="text"
                        v-bind:value="street"
                        placeholder="Street Name"
                      />
                    </div>
                  </div>
                </div>
              </div>

              <div class="field is-horizontal">
                <div class="field-label is-normal">
                  <label class="label">City</label>
                </div>
                <div class="field-body">
                  <div class="field">
                    <div class="control">
                      <input
                        class="input"
                        type="text"
                        v-bind:value="city"
                        placeholder="City"
                      />
                    </div>
                  </div>
                </div>
              </div>

              <div class="field is-horizontal">
                <div class="field-label is-normal">
                  <label class="label">PinCode</label>
                </div>
                <div class="field-body">
                  <div class="field">
                    <div class="control">
                      <input
                        class="input"
                        type="number"
                        v-bind:value="pincode"
                        placeholder="Pincode"
                      />
                    </div>
                  </div>
                </div>
              </div>

              <div class="field is-horizontal">
                <div class="field-label is-normal">
                  <label class="label">Account Status</label>
                </div>
                <div class="field-body">
                  <div class="field">
                    <div class="control">
                      <input
                        class="input"
                        type="text"
                        v-bind:value="accStatus"
                        placeholder="Account Status"
                        disabled
                      />
                    </div>
                  </div>
                </div>
              </div>
            </fieldset>
          </div>
        </article>
      </div>
    </div>

    <div class="tile is-parent">
      <article class="tile is-child">
        <div class="profile-content-div">
          <div class="profile-pic-name">
            <div>
              <img
                class="profile-pic"
                src="http://www.thegurughantal.com/uploads/7/5/8/2/75825867/delhinightclubs-5-bwxyimsnzqm_orig.jpg"
              />
            </div>

            <div>
              <h1 class="title is-2">Jen Mathew</h1>
            </div>
          </div>
        </div>
      </article>
    </div>
  </div>
</template>

<style>
.form-button {
  text-align: center;
  margin-bottom: 20px;
}
.form-button button {
  font-size: larger;
  padding-left: 20px;
  padding-right: 20px;
}
.profile-pic-name {
  width: fit-content;
  text-align: center;
}
.profile-pic {
  width: 256px;
  height: 256px;
  border-radius: 50%;
}
.profile-content-div {
  display: flex;
  justify-content: right;
}
</style>

<script>
import axios from 'axios'

export default {
  data() {
    return {
      formMode: "disabled",
      formModeButton: "Edit",
      fullName: "",
      email: "",
      mobile: "",
      role: "",
      house: "",
      street: "",
      city: "",
      pincode: "",
      accStatus: "",
      image: "",
    };
  },

  mounted(){
    this.getProfile();
  },

  methods: {
    formModeToggle() {
      if (this.formMode == "disabled") {
        this.formMode = "enabled";
        this.formModeButton = "Save";
      } else {
        this.formMode = "disabled";
        this.formModeButton = "Edit";
      }
    },

    async getProfile(){
      axios.get("/user/customer")
      .then(response => {
        var profileData = response.data;
        this.fullName = profileData.fullName
          this.email = profileData.email;
          this.mobile = profileData.mobile;
          this.role = profileData.role;
          // this.house = profileData.house
          this.street = profileData.streetName;
          this.city = profileData.cityName
          this.pincode = profileData.pincode
          this.accStatus = profileData.enabled
          this.image = profileData.image
      })
      .catch(error => {
        console.log(error);
      })
    }

  },
};
</script>
