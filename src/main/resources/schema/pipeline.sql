CREATE TABLE IF NOT EXISTS public.pipeline (
	"pipeline_id" bigserial NOT NULL,
	"name" varchar(255) NULL,
	"description" varchar(255) NULL,
	"created" timestamp with time zone NULL,
    "updated" timestamp with time zone  NULL,
    "deleted" timestamp with time zone  NULL,
	CONSTRAINT pipeline_pkey PRIMARY KEY (pipeline_id)
);