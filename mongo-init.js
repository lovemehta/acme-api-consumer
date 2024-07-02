db.createUser({
  user: "mongo_user",
  pwd: "mongo_password",
  roles: [{ role: "readWrite", db: "parcel_shops_db" }]
});
