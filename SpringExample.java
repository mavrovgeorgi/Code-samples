@Autowired
    private ExampleRepository exampleRepository;

    @Override
    public List<Item> findItems() {
        return (List<Item>) exampleRepository.findAll();
    }

    @Override
    @Transactional
    public boolean saveItem(Item item) {
        exampleRepository.save(item);
        return exampleRepository.existsByName(item.getName());
    }

    @Override
    @Transactional
    public boolean editItem(Item item) {
        exampleRepository.save(item);
        return true;
    }

    @Override
    public List<Item> findItemByID(Iterable<Long> item_ids) {

        List<Item> itemList = new ArrayList<Item>();
        Iterable<Item> iterItem = exampleRepository.findAllById(item_ids);

        iterItem.forEach(item -> {
            itemList.add(item);
        });
        return itemList;
    }