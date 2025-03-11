package org.example.springbootredis.controller;

import org.example.springbootredis.service.ProductoService;
import org.example.springbootredis.model.Producto;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public List<Producto> listarProductos() {
        return productoService.obtenerProductos();
    }

    @PostMapping
    public String agregarProducto(@RequestBody Producto producto) {
        productoService.agregarProducto(producto);
        return "✅ Producto agregado y caché limpiada";
    }

    @PostMapping("/limpiar-cache")
    @CacheEvict(value = "productos", allEntries = true)
    public String limpiarCache() {
        return "🧹 Caché de productos eliminada";
    }
}