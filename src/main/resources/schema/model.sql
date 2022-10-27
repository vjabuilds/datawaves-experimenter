CREATE TABLE IF NOT EXISTS public.ml_model (
	"mlmodel_id" bigserial NOT NULL,
	"name" varchar(255) NULL,
	"description" varchar(255) NULL,
    "version" varchar(255) NULL,
    "config_file_path" varchar(255) NULL,
    "input_shape" int[] NULL,
    "output_shape" int[] NULL,
	"created" timestamp with time zone NULL,
    "updated" timestamp with time zone  NULL,
    "deleted" timestamp with time zone  NULL,
	CONSTRAINT ml_model_pkey PRIMARY KEY (mlmodel_id)
);