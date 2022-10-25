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
                        value="joebiden@gmail.com"
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
                        value="9446020612"
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
                        value="ADMIN"
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
                        value="Ashisham"
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
                        value="Komalloor P.O"
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
                        value="Charummoodu"
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
                        value="690505"
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
                        value="Enabled"
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
                src="https://upload.wikimedia.org/wikipedia/commons/thumb/6/68/Joe_Biden_presidential_portrait.jpg/800px-Joe_Biden_presidential_portrait.jpg"
              />
            </div>

            <div>
              <h1 class="title is-2">Joe Biden</h1>
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
    mounted() {
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
      axios.get("/admin")
    }

  },
};
</script>
