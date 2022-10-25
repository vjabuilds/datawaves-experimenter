CREATE TABLE IF NOT EXISTS public.pipeline (
	"pipeline_id" bigserial NOT NULL,
	"name" varchar(255) NULL,
	"description" varchar(255) NULL,
    "created" timestamp NULL,
    "updated" timestamp NULL,
    "deleted" timestamp NULL,
	CONSTRAINT pipeline_pkey PRIMARY KEY (pipeline_id)
);