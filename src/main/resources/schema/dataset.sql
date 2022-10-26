CREATE TABLE IF NOT EXISTS public.dataset (
	dataset_id bigserial NOT NULL,
	"name" varchar(255) NULL,
	"source" varchar(255) NULL,
	"description" varchar(255) NULL,
    "type" varchar(255) NULL,
	"parent_dataset_id" int NULL,
	"parent_pipeline_id" int NULL,
    "created" timestamp with time zone NULL,
    "updated" timestamp with time zone  NULL,
    "deleted" timestamp with time zone  NULL,
	CONSTRAINT dataset_pkey PRIMARY KEY (dataset_id),
	CONSTRAINT dataset_parent_fkey FOREIGN KEY(parent_dataset_id) REFERENCES public.dataset(dataset_id),
	CONSTRAINT pipeline_parent_fkey FOREIGN KEY(parent_pipeline_id) REFERENCES public.pipeline(pipeline_id)
);