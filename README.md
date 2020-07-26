# ConfigAnnotation
This librabry allows you to save and load customs fields in a file in json easly only with annotation

# How to use
Primary, you must create an instance of ConfigInitier and init() them, if you have an application with differents side, you can pass in parameters a Side value [CLIENT, SERVER, BOTH], by default Side is Both.

Attention, the init method must be called after the initialization of your config fields.

To create a file who contains config fields : create a file and use @ConfigFile annotation, parameters are the path to the parent file and the file name without any extensions
To create a config field in your config file, just create a field with @Config annotation and in parameters you must declare a key, this key must be unique for each config field in a config file, that's his identifier in the json. You can also pass a Side in parameters if your field must only save and load in a specific Side, default is BOTH.

Now to save yours config use your instance of ConfigInitier and use method saveAll or save with the field in parameter.

All fields are load in the init of ConfigInitier
