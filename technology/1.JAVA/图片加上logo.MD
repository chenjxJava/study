``` JAVA
@RequestMapping(value = "/bookImg/waterMark/{bookid}", method = RequestMethod.GET)
    public void showBookImgWithWaterMark(HttpServletRequest request, HttpServletResponse response, @PathVariable String bookid, Integer w, Integer h) {
        OutputStream out = null;
        try {
            byte[] data = booklistController.getBookImg(request, Long.parseLong(bookid));

            // 重置图片分辨率
            if (w != null && h != null) {
                data = ImgCreator.disposeImage(new ByteArrayInputStream(data), w, h).toByteArray();
            }

            data = processWaterMark(data, request.getSession().getServletContext().getRealPath("/images/water_mark.png"));
            response.setContentType("image/jpg"); // 设置返回的文件类型
            out = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
            out.write(data); // 输出数据
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    // e.printStackTrace();
                }
            }
        }
    }


 public synchronized static ByteArrayOutputStream disposeImage(InputStream inputStream, int new_w, int new_h) throws Exception {
        BufferedImage src = javax.imageio.ImageIO.read(inputStream);
        inputStream.close();
        // 得到图片
        int old_w = src.getWidth();
        // 得到源图宽
        int old_h = src.getHeight();
        // 得到源图长
        BufferedImage newImg = null;
        // 判断输入图片的类型
        switch (src.getType()) {
            case 13:
                break;
            default:
                newImg = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);
                break;
        }
        Graphics2D g = newImg.createGraphics();
        // 从原图上取颜色绘制新图
        g.drawImage(src, 0, 0, old_w, old_h, null);
        g.dispose();
        // 根据图片尺寸压缩比得到新图的尺寸
        newImg.getGraphics().drawImage(src.getScaledInstance(new_w, new_h, Image.SCALE_SMOOTH), 0, 0, null);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(newImg, "jpg", outputStream);
        return outputStream;
    }

    public static byte[] processWaterMark(byte[] image, String markPath) throws Exception {
        BufferedImage originMark = ImageIO.read(new File(markPath));
        BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(image));
        int waterMarkWidth = bufferedImage.getWidth() * 2 / 5;
        int waterMarkHeight = waterMarkWidth * originMark.getHeight() / originMark.getWidth();
        BufferedImage waterMark = new BufferedImage(waterMarkWidth, waterMarkHeight, BufferedImage.TYPE_INT_ARGB);
        waterMark.getGraphics().drawImage(originMark.getScaledInstance(waterMarkWidth, waterMarkHeight, java.awt.Image.SCALE_SMOOTH), 0, 0, null);

        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.drawImage(waterMark, bufferedImage.getWidth() - waterMark.getWidth(), bufferedImage.getHeight() - waterMark.getHeight() * 11 / 10, new ImageObserver() {
            @Override
            public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                return false;
            }
        });
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
    ```