CREATE TABLE IF NOT EXISTS public.experiment (
	experiment_id bigserial NOT NULL,
	"name" varchar(255) NULL,
    "description" varchar(255) NULL,
	"ml_model_id" int NULL,
	"dataset_id" int NULL,
    "created" timestamp with time zone NULL,
    "updated" timestamp with time zone  NULL,
    "deleted" timestamp with time zone  NULL,
	CONSTRAINT experiment_pkey PRIMARY KEY (experiment_id),
	CONSTRAINT dataset_experiment_fkey FOREIGN KEY(dataset_id) REFERENCES public.dataset(dataset_id),
	CONSTRAINT model_experiment_fkey FOREIGN KEY(ml_model_id) REFERENCES public.ml_model(ml_model_id)
);