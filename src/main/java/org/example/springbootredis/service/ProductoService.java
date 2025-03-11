package org.example.springbootredis.service;

import org.example.springbootredis.model.Producto;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService {

    private List<Producto> productos = new ArrayList<>();

    @Cacheable(value = "productos")
    public List<Producto> obtenerProductos() {
        System.out.println("📢 Consultando productos desde la base de datos...");
        return productos;
    }

    @CacheEvict(value = "productos", allEntries = true)
    public void agregarProducto(Producto producto) {
        System.out.println("➕ Agregando producto y limpiando caché...");
        productos.add(producto);
    }
}