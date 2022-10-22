CREATE TABLE IF NOT EXISTS public.dataset (
	dataset_id bigserial NOT NULL,
	"name" varchar(255) NULL,
	"source" varchar(255) NULL,
	"description" varchar(255) NULL,
    "type" varchar(255) NULL,
    "created" timestamp NULL,
    "updated" timestamp NULL,
    "deleted" timestamp NULL,
	CONSTRAINT dataset_pkey PRIMARY KEY (dataset_id)
);

-- private Long dataset_id;
--     private String name;
--     private String source;
--     private String description;
--     private String type;