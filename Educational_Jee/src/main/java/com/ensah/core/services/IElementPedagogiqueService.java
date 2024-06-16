package com.ensah.core.services;

import java.util.List;
import com.ensah.core.bo.ElementPedag;
import com.ensah.core.utils.ExcelExporter;

public interface IElementPedagogiqueService {
    void addElementPedagogique(ElementPedag elementPedagogique);
    void updateElementPedagogique(ElementPedag elementPedagogique);
    void deleteElementPedagogique(Long id);
    ElementPedag getElementPedagogiqueById(Long id);
    List<ElementPedag> getAllElementsPedagogiques1();
	ExcelExporter prepareElementExport(List<ElementPedag> elements);
	
}
